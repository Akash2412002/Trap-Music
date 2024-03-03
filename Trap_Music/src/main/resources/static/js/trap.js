document.addEventListener('DOMContentLoaded', function() {
    // Add event listener to favorite buttons
    var favoriteButtons = document.querySelectorAll('.favorite-button');
    favoriteButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            var songId = this.getAttribute('data-song-id');
            toggleFavorite(songId);
        });
    });

    // Function to toggle favorite status
    function toggleFavorite(songId) {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/songs/togglefavorite', true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Toggle heart icon
                    var heartIcon = document.getElementById('heartIcon_' + songId);
                    if (heartIcon.classList.contains('bi-heart')) {
                        heartIcon.classList.remove('bi-heart');
                        heartIcon.classList.add('bi-heart-fill');
                    } else {
                        heartIcon.classList.remove('bi-heart-fill');
                        heartIcon.classList.add('bi-heart');
                    }
                } else {
                    console.error('Error toggling favorite status:', xhr.statusText);
                }
            }
        };
        xhr.send(JSON.stringify({ songId: songId }));
    }
});
