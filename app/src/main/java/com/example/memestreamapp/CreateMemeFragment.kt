package com.example.memestream.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.memestreamapp.data.Meme
import com.example.memestreamapp.network.RetrofitInstance
import com.example.memestreamapp.databinding.FragmentCreateMemeBinding
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class CreateMemeFragment : Fragment() {

    private var _binding: FragmentCreateMemeBinding? = null
    private val binding get() = _binding!!

    private var selectedBitmap: Bitmap? = null
    private val PICK_IMAGE = 101

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateMemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }

        binding.uploadMemeButton.setOnClickListener {
            val caption = binding.captionEditText.text.toString()
            if (selectedBitmap == null || caption.isEmpty()) {
                Toast.makeText(requireContext(), "Select an image and enter a caption", Toast.LENGTH_SHORT).show()
            } else {
                uploadMeme(selectedBitmap!!, caption)
            }
        }


    }

    private fun uploadMeme(bitmap: Bitmap, caption: String) {
        val file = File(requireContext().cacheDir, "temp.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }

        val reqFile = file.asRequestBody("image/png".toMediaType())
        val body = MultipartBody.Part.createFormData("image", file.name, reqFile)

        val userIdBody = "firebase-user-id".toRequestBody("text/plain".toMediaType()) // Replace with actual Firebase UID
        val captionBody = caption.toRequestBody("text/plain".toMediaType())

        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.memeApi.uploadMeme(body, userIdBody, captionBody)
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "Meme uploaded!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Upload failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Upload failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            uri?.let {
                val bitmap = requireContext().contentResolver.openInputStream(it)?.use { stream ->
                    android.graphics.BitmapFactory.decodeStream(stream)
                }
                bitmap?.let { bmp ->
                    selectedBitmap = bmp
                    binding.imagePreview.setImageBitmap(bmp)
                }
            }
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
