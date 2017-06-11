var express = require('express');
var fs = require('fs');
var path = require('path');
var router = express.Router();


/* GET users listing. */
router.get('/', function(req, res, next) {

	console.log(req.query.parameter);
	console.log(__dirname);
	//console.log(__filename);
	var newPath = path.resolve(__dirname, '../public/images/'+req.query.parameter);
	console.log(newPath);
	fs.exists(newPath, function(exists) {

		if (exists) {
			console.log('发送图片');
			res.sendFile(newPath);
		}
		else {
			console.log('发送图片失败！');
			res.send('获取图片失败！');
		}
	});
});

module.exports = router;