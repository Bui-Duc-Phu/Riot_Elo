// models/User.js
class User {
    constructor({ username, email, password }) {
      this.username = username;
      this.email = email;
      this.password = password;
    }
  
    // Validate email format
    isValidEmail() {
      const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return regex.test(this.email);
    }
  }
  
  module.exports = User;
  