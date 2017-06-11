var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var IosUser = new Schema({
	name: String,
	account: String,
	password: String,
	login: Boolean
});

module.exports = mongoose.model('IosUser',IosUser);