const express = require('express')
const app = express()
const port = 3000
var bodyParser = require('body-parser');  
var fs = require('fs');
const exec = require('child_process').exec;

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
    
    console.log(req.body.code);
    code=req.body.code;
    fs.writeFile('abc.py',code,function(err){
        if(err) throw err;
        console.log("file creted successfully");
        const test=exec('python abc.py');
        test.stdout.on('data',function(data){
            console.log(data);
            res.send(data);
        });
        test.stderr.on('data',function(data)
        {
            console.log(data);
        })
    
    });
    
});