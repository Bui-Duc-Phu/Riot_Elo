const express = require('express');
const mainRouter = express.Router();

const mainController = require('../controller/mainController');


mainRouter.get('/getAllUser', mainController.getAllUser);
mainRouter.post('/getUserDetailById', mainController.getDetailUserById);
mainRouter.post('/getAllMatchesByIdUser', mainController.getAllMatchesByIdUser);
mainRouter.post('/getAllTeamsMatcherByIdMatch', mainController.getAllTeamsMatcherByIdMatch);
module.exports = mainRouter;

