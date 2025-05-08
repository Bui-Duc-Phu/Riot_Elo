const errhandlerException = require("../middleware/errhandelExcetion");
const libraryRouter = require('./library')



const AllRouter  = (app) =>{
    app.use("/library", libraryRouter);
}
module.exports = AllRouter