var $usernameFld
var $passwordFld
var $firstnameFld
var $lastnameFld
var $roleFld
var $createBtn
var theTableBody
var $updateBtn
var userService = new UserServiceClient()
var users = [];

function createUser(user) {
  userService.createUser(user)
    .then(function (actualUser) {
      users.push(actualUser)
      renderUsers(users)
    })
}

var selectedUser = null
function selectUser(event) {
  var selectBtn = jQuery(event.target)
  var theId = selectBtn.attr("id")
  selectedUser = users.find(user => user._id === theId)
  $usernameFld.val(selectedUser.username)
  $passwordFld.val(selectedUser.password)
  $firstnameFld.val(selectedUser.firstname)
  $lastnameFld.val(selectedUser.lastname)
  $roleFld.val(selectedUser.role)

  //set password visible
  $passwordFld.attr("type", "text")
}

function deleteUser(event) {
    console.log(event.target)
    var deleteBtn = jQuery(event.target)
    var theClass = deleteBtn.attr("class")
    var theIndex = deleteBtn.attr("id")
    var theId = users[theIndex]._id
    console.log(theClass)
    console.log(theIndex)

    userService.deleteUser(theId)
      .then(function (status) {
        users.splice(theIndex, 1)//1 in the second parameter means we would like to remove 1 element
        renderUsers(users)
      })
}

function renderUsers(users) {
  theTableBody.empty()
  for (var i = 0; i < users.length; i++) {
    var user = users[i]
    theTableBody
      .append(`
    <tr>
        <td>${user.username}</td>
        <td>*******</td>
        <td>${user.firstname}</td>
        <td>${user.lastname}</td>
        <td>${user.role}</td>
        <td>
            <button class="btn wbdv-buffer wbdv-button-style"></button>
            <button class="btn wbdv-buffer wbdv-button-style"></button>
            <button class="btn wbdv-delete wbdv-button-style" id="${i}">
                <i class="fa-2x fas fa-times" id="${i}"></i>
            </button>
            <button class="btn wbdv-select wbdv-button-style" id="${user._id}">
                <i class="fa-2x fas fa-pencil-alt" id="${user._id}"></i>
            </button>
        </td>
    </tr>
  `)
  }

  //jQuery - $
  jQuery(".wbdv-delete")
    .click(deleteUser)//not call, hold that delete function. So we call it without paranthesis"()"
  jQuery(".wbdv-select")
    .click(selectUser)

}

function updateUser() {
  console.log(selectedUser)
  selectedUser.username = $usernameFld.val()
  selectedUser.password = $passwordFld.val()
  selectedUser.firstname = $firstnameFld.val()
  selectedUser.lastname = $lastnameFld.val()
  selectedUser.role = $roleFld.val()
  userService.updateUser(selectedUser._id, selectedUser)
    .then(function (status) {
      var index = users.findIndex(user => user._id === selectedUser._id)
      users[index] = selectedUser
      renderUsers(users)
    })

    //set password hide
    $passwordFld.attr("type", "password")
}


//we need to invoke this function when all the DOM is downloaded
function init() {
  $usernameFld = $(".wbdv-username-fld")//these are variable to bind the DOM so we put $ just before the field name.
  $passwordFld = $(".wbdv-password-fld")
  $firstnameFld = $(".wbdv-firstname-fld")
  $lastnameFld = $(".wbdv-lastname-fld")
  $roleFld = $(".wbdv-role-fld")
  $createBtn = $(".wbdv-create-btn")
  $updateBtn = $(".wbdv-update-btn")
  theTableBody = jQuery("tbody")

  $updateBtn.click(updateUser)

  $createBtn.click(() => {
      createUser({
        username: $usernameFld.val(),
        password: $passwordFld.val(),
        firstname: $firstnameFld.val(),
        lastname: $lastnameFld.val(),
        role: $roleFld.val()
      })
      $usernameFld.val("")
      $passwordFld.val("")
      $firstnameFld.val("")
      $lastnameFld.val("")
    }
  )

  userService.findAllUsers()
    .then(function (actualUsersFromServer) {
      users = actualUsersFromServer
      renderUsers(users)
    })
}
jQuery(init)//hey jQuery, wait until the DOM has loaded and browser gonna tell I am done, dom is loaded and go ahead do your thing.
