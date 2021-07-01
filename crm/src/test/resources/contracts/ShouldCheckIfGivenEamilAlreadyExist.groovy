package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should check if user with given email already exist"
    request {
        urlPath(value(consumer("/users"))) {
            queryParameters {
                parameter 'email': value(anyEmail())
            }
        }
        method GET()
        headers {
            header 'Content-Type': 'application/json'
        }

    }
    response {
        status 200
        headers {
            header 'Content-Type': 'application/json'
        }
        body(
                true
        )
    }
}