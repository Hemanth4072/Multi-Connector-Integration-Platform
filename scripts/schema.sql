CREATE TABLE connector_configs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    connector_name VARCHAR(50) NOT NULL UNIQUE,
    api_key VARCHAR(255),
    api_secret VARCHAR(255),
    base_url VARCHAR(255),
    oauth_token TEXT,
    refresh_token TEXT,
    token_expires_at BIGINT,
    additional_config JSON,
    webhook_url VARCHAR(255),
    webhook_secret VARCHAR(255),
    max_requests_per_minute INT DEFAULT 60,
    max_retries INT DEFAULT 3,
    enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE connector_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    connector_name VARCHAR(50), operation VARCHAR(100), request_id VARCHAR(100), user_id VARCHAR(100), status VARCHAR(20),
    request_payload TEXT, response_payload TEXT, error_message TEXT, execution_time_ms INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
