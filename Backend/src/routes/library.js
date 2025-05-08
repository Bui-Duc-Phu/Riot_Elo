const express = require('express');
const libraryRouter = express.Router();
const { verifyClientToken } = require('../middleware/verifyClientToken');
const { libraryController } = require('../controller');


libraryRouter.get('/newUpdateStoriesMale', verifyClientToken, libraryController.getNewUpdateStories_male);
libraryRouter.get('/newUpdateStoriesFemale', verifyClientToken, libraryController.getNewUpdateStories_female)

libraryRouter.get('/favoriteStoriesMale', verifyClientToken, libraryController.getFavoriteStories_male);
libraryRouter.get('/favoriteStoriesFemale', verifyClientToken, libraryController.getFavoriteStories_female)

libraryRouter.get('/completedStoriesMale', verifyClientToken, libraryController.getCompletedStories_male);
libraryRouter.get('/completedStoriesFemale', verifyClientToken, libraryController.getCompletedStories_female)

libraryRouter.get('/topViewCountMale', verifyClientToken, libraryController.getTopViewCountStories_male);
libraryRouter.get('/topViewCountFemale', verifyClientToken, libraryController.getTopViewCountStories_female)

module.exports = libraryRouter;
