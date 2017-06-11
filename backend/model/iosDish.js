var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Dish = new Schema({
	author: String,
	password: String,
	label: String,
	title: String,
	description: String,
	imageUrl: String
});

module.exports = mongoose.model('Dishes',Dish);