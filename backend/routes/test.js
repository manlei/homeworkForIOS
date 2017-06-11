var express = require('express');
var iosUser = require('../model/iosUser');
var router = express.Router();

/* GET one user. */
router.get('/', function(req, res, next) {
	console.log('information*************:')
	console.log(req.body);
	console.log('infor*******************');
  	res.render('index', { title: 'Express' });
});

//login and register
router.post('/', function(req,res,next) {
	console.log('information*************:')
	console.log(req.body);
	var account = req.body.account;
	var password = req.body.password;
	var login = req.body.login;
	var body = '{"account":"'+account+'","login":"'+login+'","password":"'+password+'"}';
	
	console.log('infor*******************');

//login
	if (req.body.login == true) {
		console.log(req.body.login);
		iosUser.findOne({'account':req.body.account,'password':req.body.password},'-_id account password name login', function(err,user){
			if (err) {
				console.log('error in login');
				return res.status(400).json(req.body);
			}
			else {
				console.log('result of login:'+user);
				return res.status(200).json(user);
			}
		});
	}
	//register
	else {
		iosUser.find({}, function(err,users){
			if (err) {
				return res.status(400);
			}
			else {
				for (var i = users.length - 1; i >= 0; i--) {
					if (users[i].account == req.body.account) {
						console.log('already exist,register failed');
						return res.status(400).json(req.body.account);
						break;
					}
				}
				iosUser.create(req.body, function(err,user){
					if (err) {
						return res.status(400).json(user);
					}
					else {
						console.log('register success');
						return res.status(200).json(user);
					}
				})			
			}
		});
	}
})

module.exports = router;
