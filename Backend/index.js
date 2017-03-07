var express = require('express');
var app = express();
var fs = require("fs");
var FeedMe = require('feedme');
var http = require('http');
feed = require("feed-read");
var constant = require("./const.js");
const async = require('async');

const urls = [
    //"http://www.liberte-algerie.com/article/feed",
    //"http://www.algerie-focus.com/feed/",
    //"http://www.elwatan.com/actualite/rss.xml",
    //"http://www.tsa-algerie.com/feed/",
    "http://www.algerie360.com/feed/",
];

var articles = [];

// function getFeeds(url, callback) {
//     feed(url, function(err, response) {
//         if (err) throw err;
//         var i = 0;
//         while(i < constant.NUMBER_ARTICLES) {
//             if(response.hasOwnProperty(i)) {
//                 articles.push(response[i]);
//             }
//             i++;
//         }
//     callback(err, articles);
//     });
// }

function getFeeds(url, callback) {
    let error;
    http.get(url, function (res) {
        const statusCode = res.statusCode;
        if (statusCode !== 200) {
            error = new Error(`Request Failed.\n` +
                `Status Code: ${statusCode}`);
        } else {
            var parser = new FeedMe();
            parser.on('item', function (item) {
                console.log('news:', item);
                console.log('desc', item.description);
            });
        }
        callback(error, parser);
    });
}


app.get('/notes', function (req, res) {
    res.json({ notes: "This is my notebook, check this out !" })
})

app.get('/rss', function (req, res) {
    articles = [];
    async.map(urls, getFeeds, function (err, result) {
        console.log("Request ended");
        res.send(JSON.stringify(articles));
    });
})


app.listen(3000);
