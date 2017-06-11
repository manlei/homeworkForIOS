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

router.post('/',function(req, res, next){
	console.log('post photo here! ********');
	//console.log(req.body);
	console.log(req.url);
	console.log(req.files.photo);
	console.log(JSON.parse(req.body.description));
	var tmp_path = req.files.photo.path;
	//var target_path = './public/image/'+req.files.photo.headers.name;



	// fs.readFile(tmp_path,(err,data)=> {
	// 	if (err) {
	// 		console.log(err);
	// 	}
	// 	else {
	// 		var newPath = path.resolve(__dirname,'../public/images/'+req.files.photo.originalFilename);
	// 		console.log(newPath);
	// 		fs.writeFile(newPath,data,(err)=> {
	// 			if (err) {
	// 				console.log('save error');
	// 			}
	// 			else {
	// 				console.log('save new successfully');
	// 			}
	// 		});
	// 	}
	// });

	// //文件读取的本地测试
	// var myPath = 'D:/romantic.jpeg';
	// console.log(myPath);
	// fs.readFile(myPath,(err,data) =>{
	// 	if (err) {
	// 		console.log('err');
	// 	}
	// 	else {
	// 		console.log(data);
	// 		var testPath = 'D:/1.jpeg';
	// 		console.log(testPath);
	// 		fs.writeFile(testPath,data,(err)=>{
	// 			if (err) {
	// 				console.log('err when save data');
	// 			}
	// 			else {
	// 				console.log('save data successfully');
	// 			}
	// 		});
	// 	}
	// 	console.log('finish');
	// })

	// var postData = req.files.photo;
	// var path = postData.path;

	// var filename = postData.originalFilename;

	// console.log('postData');
	// console.log(postData);
	// console.log('path');
	// console.log(path);
	// console.log('filename');
	// console.log(filename);



	// if (originalFilename) {
	// 	fs.readFile(filePath, function(err,data) {

	// 	})
	// }

	return res.status(200).send('good');
	//console.log(req.content-type);
	// if(err){
	// 	console.log('err in get task!');
	// 	return res.status(400).send('err in get tasks!');
	// }
	// else{
	// 	//console.log(req.body,req.content-type);
	// 	console.log(req.url);
	// 	console.log(req.description);
	// 	console.log('end');

	// 	return res.status(200).send("成功");
	// }
});

module.exports = router;
