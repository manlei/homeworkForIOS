var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
	console.log('information*************:')
	console.log(req.body);
	console.log('infor*******************');
  	res.render('index', { title: 'Express' });
});

router.post('/', function(req,res,next) {
	console.log('information*************:')
	console.log(req.body);
	var account = req.body.account;
	var password = req.body.password;
	var body = '{"account":"'+account+'","password":"'+password+'"}';
	console.log(JSON.parse(body));
	console.log('infor*******************');

	return res.status(200).json(JSON.parse(body));	
})

module.exports = router;
