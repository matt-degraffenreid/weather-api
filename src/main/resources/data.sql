DROP TABLE IF EXISTS weatherDTO;
CREATE TABLE IF NOT EXISTS weatherDTO (
                    id INTEGER,
                    user varchar(256),
                    temp DOUBLE,
                    feels_like DOUBLE,
                    pressure DOUBLE,
                    humidity DOUBLE,
                    created_at TIMESTAMP
);
