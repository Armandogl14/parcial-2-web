const CACHE_NAME = 'encuesta-cache-v1';
const urlsToCache = [
    '../templates/',
    '../templates/index.html',
    '../templates/Login.html',
    '../templates/encuesta.html',
    '/css/style.css',
    '/js/',
    '/js/indexedDB.js',
    '/js/geolocalizacion.js'
];

self.addEventListener('install', function(event) {
    event.waitUntil(
        caches.open(CACHE_NAME)
            .then(function(cache) {
                console.log('Opened cache');
                return cache.addAll(urlsToCache);
            })
    );
});

self.addEventListener('fetch', function(event) {
    event.respondWith(
        caches.match(event.request)
            .then(function(response) {
                if (response) {
                    return response;
                }
                return fetch(event.request);
            })
    );
});
