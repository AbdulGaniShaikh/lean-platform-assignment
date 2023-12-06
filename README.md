# Lead Platform Assignment

### Users
* User
* Mentor
* Student

## Assignment Features
* User can to rate mentor. Rating can be between 1 and 5.
* Mentor's overall rating changes after user give rating.
* User can to add review to a mentor (in 50 words).
* User can get mentor details by rating.
* Mentor can give letter of recommendation to a student.
* The  LOR will be shareable and anyone with link can see the LOR.

## Table present
* User
* Mentor
* Rating
* Recommendation
* Review
* Student

# REST APIs
## create a new user
### Request

`POST v1/api/user`

### Request Body
    {
        "username":"abdulgani"    
    }

### Responses

| Code | Description |
|------|-------------|
| 201  | CREATED     |

`Example Schema for 201`

    {
        "userId": "usr_tNvMNJez6julTtcAYplQViBreg5Esm",
        "username": "abdulgani"
    }

## get a user by id
### Request
`GET v1/api/users/:userid`

### Responses

| Code | Description |
|------|-------------|
| 200  | OK          |
| 404  | NOT FOUND   |

`Example Schema for 200`

    {
        "userId": "usr_tNvMNJez6julTtcAYplQViBreg5Esm",
        "username": "abdulgani"
    }
`Example Schema for 404`

    {
        "statusCode": 404,
        "type": "NOT FOUND",
        "message": "user not present by id :userid",
        "date": "2023-12-06T08:43:31.394+00:00"
    }
## get a list of all users
### Request
`GET v1/api/users`

### Parameters
| name       | required | default value |
|------------|----------|---------------|
| pageSize   | false    | 10            |
| pageNumber | false    | 0             |

### Responses

| Code | Description |
|------|-------------|
| 200  | OK          |


`Example Schema for 200`

    [
        {
            "userId": "1",
            "username": "user1"
        },
        {
            "userId": "2",
            "username": "user2"
        },
        {
            "userId": "3",
            "username": "user3"
        }
    ]
## create new student
### Request

`POST v1/api/student`

### Request Body
    {
        "username": "student1",
    }

### Responses

| Code | Description |
|------|-------------|
| 201  | CREATED     |

`Example Schema for 201`

    {
        "studentId": "stu_fGTjYcd7Ln1Tw8bZDuUNgMHkSOa2ph",
        "username": "student1",
        "recommendationList": null
    }

## get a student by id
### Request
`GET v1/api/students/:student_id`

### Responses

| Code | Description |
|------|-------------|
| 200  | OK          |
| 404  | NOT FOUND   |

`Example Schema for 200`

    {
        "studentId": "1",
        "username": "student1",
        "recommendationList": [
            "rec_Od6FyxLlQYQm6T4yqf7itVxhYL7xvP",
            "rec_Ya764CR54zTgb61SH69Jv5L6TQD1S3"
        ]
    }
`Example Schema for 404`

    {
        "statusCode": 404,
        "type": "NOT FOUND",
        "message": "student not present by id :studentid",
        "date": "2023-12-06T10:42:57.292+00:00"
    }

## get a list of all students
### Request
`GET v1/api/students`

### Parameters
| name       | required | default value |
|------------|----------|---------------|
| pageSize   | false    | 10            |
| pageNumber | false    | 0             |

### Responses

| Code | Description |
|------|-------------|
| 200  | OK          |


`Example Schema for 200`

    [
        {
            "studentId": "1",
            "username": "student1",
            "recommendationList": [
                "rec_Od6FyxLlQYQm6T4yqf7itVxhYL7xvP",
                "rec_Ya764CR54zTgb61SH69Jv5L6TQD1S3"
            ]
        },
        {
            "studentId": "2",
            "username": "student2",
            "recommendationList": [
                "rec_DH7dMQj7MtkQ04LP4XOSXw8SVBySDy",
                "rec_yM5WrdcmEmCSZt2j2lFRHqdB4ZGv1S"
            ]
        },
        {
            "studentId": "3",
            "username": "student3",
            "recommendationList": [
                "rec_aiaFRxy57DzqYeYjdMC2pVmFD5NkQe",
                "rec_eBoj0OR4f2Te02zaE2ryd509p1Jcp7"
            ]
        }
    ]

## recommend a student by id
### Request
`GET v1/api/students/:userid/recommend`
### Request Body
    {
        "mentorId":"1"    
    }

### Responses

| Code | Description  |
|------|--------------|
| 200  | OK           |
| 404  | NOT FOUND    |
| 401  | UNAUTHORIZED |

`Example Schema for 200`

    {
        "id": "rec_GxaPzH8bNYgR4j57vMy9GREXeZjItw",
        "timestamp": "2023-12-06T10:52:23.699+00:00",
        "mentorId": "1",
        "studentId": "1",
        "mentorName": "mentor1",
        "studentName": "student1"
    }
`Example Schema for 404`

    {
        "statusCode": 404,
        "type": "NOT FOUND",
        "message": "student/mentor not present by id 222",
        "date": "2023-12-06T10:42:57.292+00:00"
    }

`Example Schema for 401`

    {
        "statusCode": 401,
        "type": "UNAUTHORIZED",
        "message": "mentor already recommended student",
        "date": "2023-12-06T10:42:57.292+00:00"
    }
## create a new mentor
### Request

`POST v1/api/mentor`

### Request Body
    {
        "username":"abdulgani"    
    }

### Responses

| Code | Description |
|------|-------------|
| 201  | CREATED     |

`Example Schema for 201`

    {
        "mentorId": "mnt_OhuLQMXdi9oSArepQdaMmsanY9ZX5I",
        "username": "tehgan1",
        "reviews": null,
        "ratings": null,
        "rating": 0.0
    }

## get a mentor by id
### Request
`GET v1/api/mentors/:mentor_id`

### Responses

| Code | Description |
|------|-------------|
| 200  | OK          |
| 404  | NOT FOUND   |

`Example Schema for 200`

    {
        "mentorId": "1",
        "username": "mentor1",
        "reviews": [
            {
                "reviewText": "demo review1",
                "reviewId": "rev_btPNJlLqNJjaIl0XBrPl2H9fh8nerL",
                "user": {
                    "userId": "1",
                    "username": "user1"
                },
                "mentor": "1",
                "timestamp": "2023-12-06T10:48:49.395+00:00"
            },
            {
                "reviewText": "demo review2",
                "reviewId": "rev_PTx91mLy5ucmmiqJOzPYGKSysNMY0x",
                "user": {
                    "userId": "2",
                    "username": "user2"
                },
                "mentor": "1",
                "timestamp": "2023-12-06T10:48:49.395+00:00"
            },
            {
                "reviewText": "demo review3",
                "reviewId": "rev_pBZHOidaZdGM4mZMBqalLoFaKpmfqf",
                "user": {
                    "userId": "3",
                    "username": "user3"
                },
                "mentor": "1",
                "timestamp": "2023-12-06T10:48:49.395+00:00"
            }
        ],
        "ratings": [
            4
        ],
        "rating": 4.0
    }
`Example Schema for 404`

    {
        "statusCode": 404,
        "type": "NOT FOUND",
        "message": "mentor/user not present by id :userid",
        "date": "2023-12-06T08:43:31.394+00:00"
    }
## get a list of all mentors
### Request
`GET v1/api/mentors`

### Parameters
| name       | required | default value |
|------------|----------|---------------|
| pageSize   | false    | 10            |
| pageNumber | false    | 0             |
| rating     | false    | 0             |

### Responses

| Code | Description |
|------|-------------|
| 200  | OK          |
| 400  | BAD_REQUEST |


`Example Schema for 200`

    [
        {
            "mentorId": "1",
            "username": "mentor1",
            "reviews": [
                {
                    "reviewText": "demo review1",
                    "reviewId": "rev_btPNJlLqNJjaIl0XBrPl2H9fh8nerL",
                    "user": {
                        "userId": "1",
                        "username": "user1"
                    },
                    "mentor": "1",
                    "timestamp": "2023-12-06T10:48:49.395+00:00"
                },
                {
                    "reviewText": "demo review2",
                    "reviewId": "rev_PTx91mLy5ucmmiqJOzPYGKSysNMY0x",
                    "user": {
                        "userId": "2",
                        "username": "user2"
                    },
                    "mentor": "1",
                    "timestamp": "2023-12-06T10:48:49.395+00:00"
                },
                {
                    "reviewText": "demo review3",
                    "reviewId": "rev_pBZHOidaZdGM4mZMBqalLoFaKpmfqf",
                    "user": {
                        "userId": "3",
                        "username": "user3"
                    },
                    "mentor": "1",
                    "timestamp": "2023-12-06T10:48:49.395+00:00"
                }
            ],
            "ratings": [
                4
            ],
            "rating": 4.0
        },
        {
            "mentorId": "2",
            "username": "mentor2",
            "reviews": [
                {
                    "reviewText": "demo review1",
                    "reviewId": "rev_I1LZVizzbl4in1mmLLnV0IxZGswEBe",
                    "user": "1",
                    "mentor": "2",
                    "timestamp": "2023-12-06T10:48:49.411+00:00"
                },
                {
                    "reviewText": "demo review2",
                    "reviewId": "rev_MnJ2lygVaurwupOEdpdo37fjMM4yEL",
                    "user": "2",
                    "mentor": "2",
                    "timestamp": "2023-12-06T10:48:49.411+00:00"
                },
                {
                    "reviewText": "demo review3",
                    "reviewId": "rev_MqwDeKQUpfa0BB2NPYCmc9rvLfM1TR",
                    "user": "3",
                    "mentor": "2",
                    "timestamp": "2023-12-06T10:48:49.411+00:00"
                }
            ],
            "ratings": [],
            "rating": 0.0
        },
        {
            "mentorId": "3",
            "username": "mentor3",
            "reviews": [
                {
                    "reviewText": "demo review1",
                    "reviewId": "rev_iWoUxFk0nHlU7pegFWGWgeAl4lao0N",
                    "user": "1",
                    "mentor": "3",
                    "timestamp": "2023-12-06T10:48:49.426+00:00"
                },
                {
                    "reviewText": "demo review2",
                    "reviewId": "rev_JsMg83tYHFyAyvKQGhBeFOi93gsH0S",
                    "user": "2",
                    "mentor": "3",
                    "timestamp": "2023-12-06T10:48:49.426+00:00"
                },
                {
                    "reviewText": "demo review3",
                    "reviewId": "rev_L4iYFL3oTi3S1EJvJq2S2ZYvw8djv3",
                    "user": "3",
                    "mentor": "3",
                    "timestamp": "2023-12-06T10:48:49.442+00:00"
                }
            ],
            "ratings": [],
            "rating": 0.0
        },
        {
            "mentorId": "mnt_OhuLQMXdi9oSArepQdaMmsanY9ZX5I",
            "username": "tehgan1",
            "reviews": [],
            "ratings": [],
            "rating": 0.0
        }
    ]

`Example schema for 400`

    {
        "statusCode": 400,
        "type": "BAD_REQUEST",
        "message": "rating should be between 1 and 5",
        "date": "2023-12-06T11:15:20.008+00:00"
    }

## rate a mentor by id
### Request

`POST v1/api/mentors/:mentor_id/rate`

### Request Body
    {
        "rating":5
        "userId":"1"    
    }

### Responses

| Code | Description  |
|------|--------------|
| 200  | OK           |
| 401  | UNAUTHORIZED |
`Example Schema for 200`

    {
        "message": "mentor with id :mentor_id was rated"
    }

`Example Schema for 401`

    {
        "statusCode": 401,
        "type": "UNAUTHORIZED",
        "message": "User already rated the mentor",
        "date": "2023-12-06T10:42:57.292+00:00"
    }
## add review to a mentor by id
### Request

`POST v1/api/mentors/:mentor_id/review`

### Request Body
    {
        "reviewText":":reviewText"
        "userId":"1"    
    }

### Responses

| Code | Description |
|------|-------------|
| 200  | OK          |
| 404  | NOT FOUND   |
| 400  | BAD_REQUEST |

`Example Schema for 200`

    {
        "message": "new review was added"
    }

`Example Schema for 404`

    {
        "statusCode": 404,
        "type": "NOT FOUND",
        "message": "user/mentor not present by id :id",
        "date": "2023-12-06T11:15:20.008+00:00"
    }

`Example schema for 400`

    {
        "statusCode": 400,
        "type": "BAD_REQUEST",
        "message": "review should be under 50 words",
        "date": "2023-12-06T11:15:20.008+00:00"
    }

## letter of recommendation by its id
### Request

`POST /recommendations/:recommendation_id`

### Responses

| Code | Description |
|------|-------------|
| 200  | OK          |
| 404  | NOT FOUND   |

`Example Schema for 200`

    {
        "id": "rec_v2YfHY3UESGgFD3cxTy9uKqodVSV44",
        "timestamp": "2023-12-06T11:03:54.495+00:00",
        "mentorId": "2",
        "studentId": "1",
        "mentorName": "mentor2",
        "studentName": "student1"
    }

`Example Schema for 404`

    {
        "statusCode": 404,
        "type": "NOT FOUND",
        "message": "Recommendation not present by id :id",
        "date": "2023-12-06T11:15:20.008+00:00"
    }


### Author
* [Shaikh Abdul Gani](https://github.com/AbdulGaniShaikh)