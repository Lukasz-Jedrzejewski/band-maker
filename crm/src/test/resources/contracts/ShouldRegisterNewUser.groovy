package contracts

import org.springframework.cloud.contract.spec.Contract

import java.util.regex.Pattern

Contract.make {
    description "should register new user"
    request {
        url "/users"
        method POST()
        headers {
            header 'Content-Type': 'application/json'
        }
        body (
                email: "contractsamail@gmail.com",
                password: "HereShouldBeEncodedPassword",
                userType: "MUSICIAN"
        )

    }
    response {
        status 201
        headers {
            header 'Content-Type': 'application/json'
        }
        body(
                id: regex(Pattern.compile('[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}')),
                email: fromRequest().body("email"),
                password: fromRequest().body("password"),
                userType: fromRequest().body("userType"),
                createDate: regex(Pattern.compile(/[\S\s]+/)),
                updateDate: regex(Pattern.compile(/[\S\s]+/)),
                removed: false
        )
    }
}