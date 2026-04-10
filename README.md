# Deploy on Heroku with MongoDB
```
heroku create spring-boot-mongodb
git push heroku main
heroku config:set MONGODB_URI=${MONGODB_URI}
heroku open
```