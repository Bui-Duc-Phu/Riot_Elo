const errhandlerException = require("../middleware/errhandelExcetion");
const mainRouter = require('./mainRouter')



const AllRouter  = (app) =>{
    app.use("/main", mainRouter);
    app.use(errhandlerException);
}
module.exports = AllRouter