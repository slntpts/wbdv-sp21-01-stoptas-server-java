function UserServiceClient() {
  this.createUser = createUser;
  this.findAllUsers = findAllUsers;
  this.findUserById = findUserById;
  this.deleteUser = deleteUser;
  this.updateUser = updateUser;//"this" used here is different than the Java. Here this means that the data bound to the that created UserServiceClient. We cannot use it in fetch below.
  this.url = 'https://wbdv-generic-server.herokuapp.com/api/001597039/users';
  var self = this;//Instead of "this", we use "self" here which means the original data
  function createUser(user) {
    return fetch(self.url, {
      method: 'POST',
      headers: {
        'content-type': 'application/json'
      },
      body: JSON.stringify(user)
    }).then(function (response) {
      return response.json()
    })
  }
  function findAllUsers() {
    return fetch(self.url)
      .then(function (response) {
        return response.json()
    })
  }
  function findUserById(userId) {

  }

  function updateUser(userId, user) {
    return fetch(`${self.url}/${userId}`, {
      method: 'PUT',
      headers: {
        'content-type': 'application/json'
      },
      body: JSON.stringify(user)
    }).then(response => response.json())
  }
  function deleteUser(userId) {
    return fetch(`${self.url}/${userId}`,
      {method: 'DELETE'})
  }
}