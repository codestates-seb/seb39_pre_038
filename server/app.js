const express = require("express");
const path = require("path");
// const cors = require("cors");

const app = express();
const PORT = 80;
const createPath = (fileURL = "") =>
  path.join(__dirname, `../frontend/build`, fileURL);

app.use(express.json());
app.use(express.static(createPath()));

app.get("/", (req, res) => res.sendFile(createPath("/index.html")));
app.get("*", (req, res) => res.sendFile(createPath("/index.html")));

app.listen(PORT, () => {
  console.log(`http://localhost:${PORT}`);
});
