# SpringBoot MySQL Read-Write Splitting Example

This repository contains example code for implementing MySQL read-write splitting with SpringBoot.

## Version Description

- `tag v0.1`: A basic version that shows how to implement read-write splitting by directly connecting to the database using SpringBoot with JDBC.
- `tag v0.2`: Demonstrates how to achieve read-write splitting with SpringBoot and MyBatis, with support for automatic routing of read and write requests.
- `tag v0.3`: Adds the feature of failover from slave database failures under the premise of having data source exception monitoring capabilities.

## How to Use

After cloning the repository, you can check out different tags to see the specific implementation of each version.

```bash
git clone https://github.com/hust-suwb/spring-boot-read-and-write-datasource-demo.git
cd spring-boot-read-and-write-datasource-demo
git checkout tags/v0.1 # or v0.2 to view different versions
