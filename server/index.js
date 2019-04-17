const express = require('express')
const app = express()
const port = 3000
var bodyParser = require('body-parser');  

var urlencodedParser = bodyParser.urlencoded({ extended: false })  
app.use(express.static('public'));  

//app.get('/', (req, res) => res.send('Hello World!'))

app.listen(port, () => console.log(`Example app listening on port ${port}!`))
app.get('/',function(req,res){
    var  code=req.param('code');
    console.log("code is " +code);
})
app.post('/',urlencodedParser,function(req,res)
{
    
    console.log(req.code);
});