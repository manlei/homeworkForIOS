var express = require('express');
var fs = require('fs');
var path = require('path');
var iosDish = require('../model/iosDish.js');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {

	console.log('iosDish information*************:')
	console.log(req.query.parameter);
	console.log('infor*******************');

	//return dishes based on req.body

	switch (req.query.parameter) {
		//get ten dishes randomly
		case 'random': {
			iosDish.find({}).limit(10).exec(function(err,dishes) {
				if (err) {
					return res.status(400);
				}
				else {
					console.log('random');
					//random start
					var start = Math.round(Math.random()*(dishes.length-1));
					start = start >= 0 ? start : 0;
					console.log('start:'+start);
					var temp = new Array();
					var count = 0;
					var min = (dishes.length-start > 10) ? 10 : dishes.length-start;
					var end = start + min;  
					for (var i = start; i < end; i++) {
						temp[count] = dishes[i];
						count++;
					}
					console.log('temp');
					console.log(temp);
					return res.status(200).json(temp);
				}
			});
			break;
		}
		//get some kind of dishes
		default: {
			//var query = iosDish.where('label',req.body.parameter);

			iosDish.where({'label':req.query.parameter}).limit(10).find( function(err,dishes) {
				if (err) {
					return res.status(400);
				}
				else {
					console.log('kind:'+req.query.parameter);
					console.log(dishes);

					var start = Math.round(Math.random()*(dishes.length-1));
					start = start >= 0 ? start : 0;
					console.log('start:'+start);
					var temp = new Array();
					var count = 0;
					var min = (dishes.length-start > 10) ? 10 : dishes.length-start;
					var end = start + min;  
					for (var i = start; i < end; i++) {
						temp[count] = dishes[i];
						count++;
					}
					console.log('temp');
					console.log(temp);
					return res.status(200).json(temp);		
				}
			});
			break;
		}	
	}

});

router.post('/', function(req,res,next) {
	console.log('iosDish information*************:')

	console.log(req.files);
	console.log(req.url);
	console.log(req.body.description);
	console.log(JSON.parse(req.body.description));
	//console.log(req.body);
	//var account = req.body.account;
	//var password = req.body.password;
	//var body = '{"account":"'+account+'","password":"'+password+'"}';
	//console.log(JSON.parse(body));
	console.log('infor*******************');

	//save the picture
	var tmp_path = req.files.photo.path;
	console.log(tmp_path);
	//var target_path = './public/image/'+req.files.photo.headers.name;

	fs.readFile(tmp_path,(err,data)=> {
		if (err) {
			console.log(err);
		}
		else {
			var newPath = path.resolve(__dirname,'../public/images/'+req.files.photo.originalFilename);
			console.log(newPath);
			fs.writeFile(newPath,data,(err)=> {
				if (err) {
					console.log('save error');
				}
				else {
					console.log('save new successfully');
				}
			});
		}
	});

	//save the description
	iosDish.create(JSON.parse(req.body.description), function(err,dish) {
		if (err) {
			console.log('publish dish error');
			return res.status(400).json(JSON.parse(req.body.description));
		}
		else {
			console.log('publish dish success');
			console.log(dish);
			return res.status(200).json(dish);
		}
	});

});

module.exports = router;
