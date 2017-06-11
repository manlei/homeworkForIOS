var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Task2 = new Schema({
	title:String,
	date:{type:Date,default:Date.now}
});

module.exports = mongoose.model('Login',Task2);