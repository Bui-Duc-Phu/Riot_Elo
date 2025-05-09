
const config = require('./configs/import')
// import 
const { port,
    hostname,
    printColoredConsole,
    conFigViewEngine,
    express,
    pool,
    AllRouter
} = config;

const app = express();

// viewEngine  {  dotEnv, morgan, bodyParser, cors}
conFigViewEngine(app)


// router
AllRouter(app)

//Connect Database
pool.getConnection()
  .then(() => {
    console.log('✅ Database connected');
  })
  .catch((err) => {
    console.error('❌ Error connecting to database:', err);
  });


app.listen(port,hostname, () => {
    printColoredConsole('violet', 'Server Running ---> listening on port ' + port);
});