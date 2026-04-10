# Deploy on Heroku with MongoDB
```
heroku create spring-boot-mongodb
git push heroku main
heroku config:set MONGODB_URI=${MONGODB_URI}
heroku open
```

## Spring Boot 實作 $lookup
Add categories
```
db.categories.insertOne({ "_id": "c1", "title": "手機與配件" })
```
Update iPhone 15
```
db.products.updateOne({ name: "iPhone 15"}, { $set: { catId: "c1"}})
```