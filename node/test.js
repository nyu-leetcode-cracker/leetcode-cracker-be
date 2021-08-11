const axios = require('axios');

axios.interceptors.request.use((config) => {
  return config;
})

const config = {
  method: 'post',
  url: 'https://leetcode-cn.com/graphql/',
  headers: {
    'content-type': 'application/json'
  },
  data: {
    operationName: 'recentSubmissions',
    variables: {
      userSlug: process.argv[2]
    },
    query:
      'query recentSubmissions($userSlug: String!) {\n  recentSubmissions(userSlug: $userSlug) {\n    status\n    lang\n    question {\n      questionFrontendId\n      title\n      translatedTitle\n      titleSlug\n    }\n    submitTime\n  }\n}\n'
  }
};

axios(config)
  .then(function (response) {
    // console.log(Object.keys(response.request.socket))
    // console.log(response.headers['set-cookie'])
    console.log(JSON.stringify(response.data))
  })
  .catch(function (error) {
    console.log(error)
  })
