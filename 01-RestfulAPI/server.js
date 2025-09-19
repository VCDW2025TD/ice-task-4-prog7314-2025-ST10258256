const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 5000;

const uri = process.env.MONGO_URI;

// Middleware
app.use(cors());
app.use(express.json());

// Optional test route for Render health check
app.get('/', (req, res) => {
  res.send('MemeStream API is running!');
});

// Routes
app.use('/memes', require('./routes/memes'));

// MongoDB Connection
mongoose.connect(uri, { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => console.log("Connected to MongoDB"))
  .catch(err => console.error("MongoDB connection failed:", err.message));

app.listen(PORT, () => {
  console.log(`🚀 Server running on port ${PORT}`);
});