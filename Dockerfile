# Use a Maven image with OpenJDK 11
FROM maven:3.8.5-openjdk-11

# Set the working directory
WORKDIR /src

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Install system dependencies required for Playwright
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    curl \
    libx11-xcb1 \
    libxcomposite1 \
    libxdamage1 \
    libxrandr2 \
    libxtst6 \
    libnss3 \
    libasound2 \
    libgbm1 \
    libgtk-3-0 \
    libxshmfence1 \
    ca-certificates \
    fonts-liberation \
    libappindicator3-1 \
    libatspi2.0-0 \
    libdrm2 \
    libnspr4 \
    libnss3 \
    lsb-release \
    xdg-utils \
    xvfb \
    x11-xserver-utils \
    && rm -rf /var/lib/apt/lists/*

# Copy the source code
COPY src ./src

# Install Playwright dependencies
RUN mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install-deps"

# Run the tests with Maven inside xvfb
CMD ["sh", "-c", "xvfb-run --auto-servernum -- mvn verify -Dproduct=${product} && mvn allure:report"]
