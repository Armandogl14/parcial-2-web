let db;

window.onload = function() {
    let request = window.indexedDB.open("EncuestasDB", 1);

    request.onerror = function() {
        console.log('Database failed to open');
    };

    request.onsuccess = function() {
        console.log('Database opened successfully');
        db = request.result;
    };

    request.onupgradeneeded = function(e) {
        let db = e.target.result;
        let objectStore = db.createObjectStore('encuestas', { keyPath: 'id', autoIncrement: true });
        objectStore.createIndex('nombre', 'nombre', { unique: false });
        objectStore.createIndex('sector', 'sector', { unique: false });
        objectStore.createIndex('nivelEscolar', 'nivelEscolar', { unique: false });
        objectStore.createIndex('latitud', 'latitud', { unique: false });
        objectStore.createIndex('longitud', 'longitud', { unique: false });
        console.log('Database setup complete');
    };
}