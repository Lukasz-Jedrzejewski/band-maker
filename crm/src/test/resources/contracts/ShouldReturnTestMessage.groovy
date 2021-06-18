import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return test message"
    request {
        url value(
                consumer("/api/s2s/test"),
                producer("/test")
        )
        method GET()
    }
    response {
        status 200
        body("Hello crm and rest template")
    }
}