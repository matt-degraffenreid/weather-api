DROP TABLE IF EXISTS weather;
CREATE TABLE IF NOT EXISTS weather (
                    id INTEGER,
                    user varchar(256),
                    temp DOUBLE,
                    feels_like DOUBLE,
                    pressure DOUBLE,
                    humidity DOUBLE,
                    created_at TIMESTAMP,
                    exited BOOLEAN
);
