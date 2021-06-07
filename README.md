## Problem statement:
Every company and their respective teams have lot of jenkins job running everyday. these jenkins jobs can contain smoke tests, Integration tests, sanity tests etc.these jobs are maintained invidually.for each job, if email is configured, a new email will get triggered after test execution.it takes lot of time to go through each and every job email and then analyse the cause of failure and success.To overcome this problem, I have developed this utility.

## Solution:
This utility will solve the above problem. It will fetch the job details from each and every jenkins job and it consolidates all the details and it sends a single email with a table containing details of all the jenkins job. instead of n number of emails for n jenkins job, only one email will get triggered.

This is generic utility where each and every company or any team or any individual can use it. it will saves lot of time.


## How To Run It:
1. In the config.properties file, give all the jenkins jobs urls for which you want to include in the report.
2. Add the details related to your email
3. In the app-password, go to your email settings and find apppassword and use that.


## Output:
please refer to the image(AllJenkinsJobsConsolidator.png) attached in the project files
