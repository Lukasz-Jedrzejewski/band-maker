import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return test message"
    request {
        url "/api/s2s/test"
        method GET()
    }
    response {
        status 200
        body("Hello crm and rest template")
    }
}