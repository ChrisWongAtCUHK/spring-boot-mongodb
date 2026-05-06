# Deploy on Red Hat Developer with MongoDB

## Docker on Red Hat Developer
### Build
```
docker build --platform linux/amd64 -t chriswongatcuhk/spring-boot-mongodb .
```
### Run with environment variable
```
docker run -p 8080:8080 -e MONGODB_URI chriswongatcuhk/spring-boot-mongodb
```
### Push to Docker Hub
```
docker push chriswongatcuhk/spring-boot-mongodb
```

## Set environment variables
Workloads->Topology
  Actions->Edit Service
    YAML
      
### Logs
Logs 標籤頁也就不會再消失了
```
oc get ksvc
oc patch ksvc spring-boot-mongodb --type merge -p '{"spec":{"template":{"metadata":{"annotations":{"autoscaling.knative.dev/minScale":"1"}}}}}'
```

### Red Hat Developer url
https://spring-boot-mongodb-chriswong924-dev.apps.rm3.7wse.p1.openshiftapps.com

## Spring Boot 實作 $lookup
Add categories
```
db.categories.insertOne({ "_id": "c1", "title": "手機與配件" })
```
Update iPhone 15
```
db.products.updateOne({ name: "iPhone 15"}, { $set: { catId: "c1"}})
```

## 在 REST Controller 加入一個「包含分類資訊」的產品查詢介面
