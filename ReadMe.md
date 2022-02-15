Get call for getting product price for productId.
```shell
http://localhost:8080/app/service/price?productId=1
```

Post call for creating a purchase.
```shell
http://localhost:8080/app/service/order/purchase

Request Body -
[
    {
        "productId": 1,
        "qnty": 1
    },
    {
        "productId": 2,
        "qnty": 1
    }
]
```

