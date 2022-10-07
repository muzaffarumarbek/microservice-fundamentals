CREATE TABLE IF NOT EXISTS song (
                                         id SERIAL PRIMARY KEY,
                                         audio varchar(64) NOT NULL,
    file_url varchar(328) NOT NULL,
    is_upload_success_full boolean
    );