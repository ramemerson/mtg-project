document.addEventListener('DOMContentLoaded', () => {
    let page = 0;
    const size = 25;
    const cardContainer = document.getElementById('cardContainer');
    const searchInput = document.getElementById('searchInput');
    const searchButton = document.getElementById('searchButton');
    const paginationContainer = document.getElementById('paginationContainer');
    let totalPages = 0;

    const fetchTotalPages = async () => {
        try {
            const response = await fetch(`/browse/totalPages?size=${size}`, {
                credentials: 'same-origin'
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            totalPages = await response.json();
            renderPagination();
        } catch (error) {
            console.error('Error fetching total pages:', error);
        }
    };

    const fetchCards = async () => {
        try {
            const response = await fetch(`/browse/page?page=${page}&size=${size}`, {
                credentials: 'same-origin'
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const cards = await response.json();
            cardContainer.innerHTML = '';
            cards.forEach(card => {
                const cardElement = document.createElement('tr');
                cardElement.innerHTML = `
                    <td><img class="card-img" src="${card.image_uris.small}" alt="Card Image"></td>
                    <td>${card.name}</td>
                    <td>${card.prices.eur}</td>
                    <td>${card.rarity}</td>
                    <td>${card.released}</td>
                    <td>${card.type_line}</td>
                    <td>${card.color}</td>
                `;
                cardContainer.appendChild(cardElement);
            });
            renderPagination();
        } catch (error) {
            console.error('Error fetching cards:', error);
        }
    };

    const searchCards = async () => {
        const query = searchInput.value.trim();
        if (query) {
            try {
                const response = await fetch(`/browse/search?query=${query}`, {
                    credentials: 'same-origin'
                });
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const cards = await response.json();
                cardContainer.innerHTML = '';
                cards.forEach(card => {
                    const cardElement = document.createElement('tr');
                    cardElement.innerHTML = `
                        <td><img class="card-img" src="${card.image_uris.small}" alt="Card Image"></td>
                        <td>${card.name}</td>
                        <td>${card.prices.eur}</td>
                        <td>${card.rarity}</td>
                        <td>${card.released}</td>
                        <td>${card.type_line}</td>
                        <td>${card.color}</td>
                    `;
                    cardContainer.appendChild(cardElement);
                });
            } catch (error) {
                console.error('Error searching cards:', error);
            }
        }
    };

    const renderPagination = () => {
        paginationContainer.innerHTML = '';
        const maxPagesToShow = 10;
        const startPage = Math.floor(page / maxPagesToShow) * maxPagesToShow;
        const endPage = Math.min(startPage + maxPagesToShow, totalPages);

        const previousButton = document.createElement('button');
        previousButton.innerHTML = '&laquo;';
        previousButton.addEventListener('click', () => {
            if (page > 0) {
                page--;
                fetchCards();
            }
        });
        paginationContainer.appendChild(previousButton);

        for (let i = startPage; i < endPage; i++) {
            const pageButton = document.createElement('button');
            pageButton.innerText = i + 1;
            if (i === page) {
                pageButton.classList.add('active');
            }
            pageButton.addEventListener('click', () => {
                page += i;
                fetchCards();
            });
            paginationContainer.appendChild(pageButton);
        }

        const nextButton = document.createElement('button');
        nextButton.innerHTML = '&raquo;';
        nextButton.addEventListener('click', () => {
            if (page < totalPages - 1) {
                page++;
                fetchCards();
            }
        });
        paginationContainer.appendChild(nextButton);
    };

    searchButton.addEventListener('click', searchCards);

    fetchTotalPages();
    fetchCards();
});
