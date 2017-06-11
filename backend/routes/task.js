var express = require('express');
var router = express.Router();
var Login = require('../model/task.js');

var temp;

function show(){
	console.log('temp:');
	console.log(temp);
}

router.get('/',function(req,res,next){
	// Login.find({},function(err,tasks){
	// 	if(err){
	// 		console.log('err in get task!');
	// 		return res.status(400).send('err in get!');
	// 	}
	// 	else{
	// 		temp = tasks;
	// 		console.log(req.url);
	// 		show();
	// 		//console.log(tasks);
	// 		return res.status(200).json(tasks);
	// 	}
	// })

	Login.find({user: 'caiji'}, function(err,tasks){
			if(err){
				console.log('err in get task!');
				return res.status(400).send('err in get tasks!');
			}
			else{
				console.log(res);
				return res.status(200).json(tasks);
			}
	})
});

module.exports = router;