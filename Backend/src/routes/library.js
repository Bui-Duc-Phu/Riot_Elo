const express = require('express');
const libraryRouter = express.Router();
const { verifyClientToken } = require('../middleware/verifyClientToken');
const { libraryController } = require('../controller');



libraryRouter.post('/insertUser', libraryController.insertUser);

module.exports = libraryRouter;
