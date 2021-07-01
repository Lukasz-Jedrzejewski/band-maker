package contracts

import org.springframework.cloud.contract.spec.Contract

import java.util.regex.Pattern

Contract.make {
    description "should save new personal data"
    request {
        urlPath(value(consumer(regex('/personal-data/[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}'))))
        method POST()
        headers {
            header 'Content-Type': 'application/json'
        }
        body(
                name: value(anyNonEmptyString()),
                surname: value(anyNonEmptyString()),
                city: value(anyNonEmptyString()),
                alias: value(anyNonEmptyString()),
                instrument: "BASS_GUITAR",
                vocal: false,
                description: value(anyNonEmptyString())
        )
    }
    response {
        status 201
        headers {
            header 'Content-Type': 'application/json'
        }
        body([
                id: value(anyUuid()),
                name: value(anyNonEmptyString()),
                surname: value(anyNonEmptyString()),
                city: value(anyNonEmptyString()),
                alias: value(anyNonEmptyString()),
                instrument: value(anyNonEmptyString()),
                vocal: false,
                description: value(anyNonEmptyString()),
                user: ([
                        id: value(anyUuid()),
                        email: value(anyEmail()),
                        password: value(anyNonEmptyString()),
                        userType: value(anyNonEmptyString()),
                        createDate: regex(Pattern.compile(/[\S\s]+/)),
                        updateDate: regex(Pattern.compile(/[\S\s]+/)),
                        removed: false
                ]),
                createDate: regex(Pattern.compile(/[\S\s]+/)),
                updateDate: null,
                removed: false
        ])
    }
}