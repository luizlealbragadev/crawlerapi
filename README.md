# Crawlerapi

Crawlerapi is a RESTful Web Service designed to be a solution to a problem proposed by Trustly in its job selection process.
The problem is to develop an API that returns the total number of lines and the total number of bytes of all files in a given public Github repository, grouped by file extension.

## Installation

To install the project you need to follow some simple steps.

```
1 - Clone the project to your machine
2 - Import as a Maven project to your IDE
3 - Use Apache Tomcat v8.5 as your server
```

At this point everything should be done and working

## How to use

To use it, first, you need to know the only existing Endpoint that can be seen below.

```
http://localhost:8080/crawlerapi/crawler/craw
```

Knowing this, the second important information is the content of the header that needs to be passed in the call of the api. Which is only:

```
Content-Type:application/json
```

Now we need to pass the parameters of the body and just below you can see an example of what the request body should look like:

```
{
    "url": "https://github.com/bradtraversy/vanillawebprojects", //This field is required
    "responseSizeFormat": "bytes" //This field isn't required
}
```

Now what you need to know about the body parameters is that:

```
1 - url
    1.1 - The "url" parameter is necessary.
    1.2 - It must be a url from a public github repository.
    1.3 - Example: "https://github.com/bradtraversy/vanillawebprojects".
2 - responseSizeFormat.
    2.1 - The "responseSizeFormat" parameter is an optional field that defines the format of the size of the returned data.
    2.2 - You can either put it on the body or not.
        2.2.1 - If you don't put it on the body the default return type will be Bytes.
        2.2.2 - If you put it on the body your options are below.
            2.2.2.1 - bytes
            2.2.2.2 - kb
            2.2.2.3 - mb
            2.2.2.4 - gb
            2.2.2.5 - tb
        2.2.3 - Anything different will return in Bytes.
        2.2.4 - It isn't case sensitive
```

Finally, if all steps were completed when making the request, you will get a return similar to the one below:

```
{
    "sizeFormat": "Bytes",
    "data": {
        "txt": {
            "size": 338,
            "lines": 12
        },
        "css": {
            "size": 937,
            "lines": 70
        },
        "md": {
            "size": 406,
            "lines": 13
        },
        "js": {
            "size": 5191.68,
            "lines": 240
        },
        "html": {
            "size": 844,
            "lines": 27
        }
    }
}
```

## Final  considerations
It is worth mentioning a great obstacle that I found in the middle of the road, which was the security of github, github has a limit of access on their pages and when this limit is over, it blocks the IP address in 60 seconds and can be extended up to 1 hour and this made the development with threads impossible since the generated blocking time increased faster.
It is also worth mentioning that it is not useful to persist any captured data to generate a cache and return a second faster request because once the repository is updated I will be forced to go through all the files again.
