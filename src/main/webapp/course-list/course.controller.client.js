var titleFld
var $seatsFld
var $semesterFld
var $createBtn
var addCourseBtn
var theTableBody
var $updateBtn
var courseService = new CourseServiceClient()

function addCourse() {//anonymous(no-name) function - lambda function: it's not meant to be called from anywhere else but inside of click function.
  createCourse({
    title: 'NEW COURSE',
    seats: 100,
    semester: 'Fall'
  })
}
var courses = [];

//playing around
//var theHeading = jQuery("h1#the-course-list-heading")//changes the specific h1 with the #id
//theHeading
//    .html("Course List Editor")
//    .css("background-color", "blue")
//    .css("color", "yellow")
//    .append(" - add, remove courses!")
//    .append("<button>Go!</button>")

function createCourse(course) {
  courseService.createCourse(course)
    .then(function (actualCourse) {
      courses.push(actualCourse)
      renderCourses(courses)
    })
}

//playing around
//createCourse({title: 'CS1111', seats: 11, semester: 'Spring'})
//createCourse({title: 'CS2222', seats: 11, semester: 'Spring'})
//createCourse({title: 'CS3333', seats: 11, semester: 'Spring'})
//createCourse({title: 'CS4444', seats: 11, semester: 'Spring'})

var selectedCourse = null
function selectCourse(event) {
  var selectBtn = jQuery(event.target)
  var theId = selectBtn.attr("id")
  selectedCourse = courses.find(course => course._id === theId)
  titleFld.val(selectedCourse.title)
  $seatsFld.val(selectedCourse.seats)
  $semesterFld.val(selectedCourse.semester)
}

function deleteCourse(event) {
    console.log(event.target)
    var deleteBtn = jQuery(event.target)
    var theClass = deleteBtn.attr("class")
    var theIndex = deleteBtn.attr("id")
    var theId = courses[theIndex]._id
    console.log(theClass)
    console.log(theIndex)

    courseService.deleteCourse(theId)
      .then(function (status) {
        courses.splice(theIndex, 1)//1 in the second parameter means we would like to remove 1 element
        renderCourses(courses)
      })
}

function renderCourses(courses) {
  theTableBody.empty()
  for (var i = 0; i < courses.length; i++) {
    var course = courses[i]
    theTableBody
      .prepend(`
    <tr>
        <td>${course.title}</td>
        <td>${course.seats}</td>
        <td>${course.semester}</td>
        <td>
            <button class="wbdv-delete" id="${i}">Delete</button>
            <button class="wbdv-select" id="${course._id}">Select</button>
        </td>
    </tr>
  `)
  }

  //jQuery - $
  jQuery(".wbdv-delete")
    .click(deleteCourse)//not call, hold that delete function. So we call it without paranthesis"()". Without parameter, it means, create an event, with parameter it means read the event.
  jQuery(".wbdv-select")
    .click(selectCourse)
}
// renderCourses(courses)

function updateCourse() {
  console.log(selectedCourse)
  selectedCourse.title = titleFld.val()
  selectedCourse.seats = $seatsFld.val()
  selectedCourse.semester = $semesterFld.val()
  courseService.updateCourse(selectedCourse._id, selectedCourse)
    .then(function (status) {
      var index = courses.findIndex(course => course._id === selectedCourse._id)
      courses[index] = selectedCourse
      renderCourses(courses)
    })
}


//we need to invoke this function when all the DOM is downloaded
function init() {
  titleFld = $(".wbdv-title-fld")//these are variable to bind the DOM so we put $ just before the field name.
  $seatsFld = $(".wbdv-seats-fld")
  $semesterFld = $(".wbdv-semester-fld")
  $createBtn = $(".wbdv-create-btn")
//  addCourseBtn = jQuery("#wbdv-create-course")
//  addCourseBtn.click(addCourse)
  $updateBtn = $(".wbdv-update-btn")
  theTableBody = jQuery("tbody")

  $updateBtn.click(updateCourse)

  $createBtn.click(() => {
      createCourse({
        title: titleFld.val(),
        seats: $seatsFld.val(),
        semester: $semesterFld.val()
      })
      titleFld.val("")
      $seatsFld.val()
    }
  )

    /**
     * All the td buttons that we defined above are not exist until we come to main. They'll exist when we call findAllCourses.
     */
  courseService.findAllCourses()
    .then(function (actualCoursesFromServer) {
      courses = actualCoursesFromServer
      renderCourses(courses)// All the delete buttons that we defined above are not exist until we call renderCourses.
    })
}
jQuery(init)//hey jQuery, wait until the DOM has loaded and browser gonna tell I am done, dom is loaded and go ahead do your thing.
