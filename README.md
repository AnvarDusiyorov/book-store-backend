
POST : /signup , required JSON:
```
 { 
	"email" : "someEmail",
	"password" : "somePassword",
	"firstName" : "someFirstName",
	"secondName : "someSecondName",
	"phoneNumber" : "someNumber"
}
```

RESPONSE : 
```
	if successful 
		HttpStatus will be HttpStatus.CREATED
		body JSON: 
		{
			"created" : true,
			"reason": null
		}
	else
		HttpStatus will be HttpStatus.CONFLICT
		body JSON:
		{
			"created" : false,
			"reason": "someReason"
		}
```
---

POST : /login, required JSON:
```
{
	"username" : "someUser@someMail",
	"password" : "somePassword"
}
```

RESPONSE:
After successful authentication, returned header will contain 'Authorization' : 'Bearer createdToken'

---
GET : /home

RESPONSE:
```
{
    "genres": [
        "Classics",
        "Fantasy",
        "Philosophy",
        "Children's"
    ],
    "bestsellers": [
        {
            "title": "Harry Potter and the Sorcerer's Stone",
            "authors": [
                {
                    "creativePseudonym": "J.K. Rowling"
                }
            ],
            "rating": 5.0
        },
        {
            "title": "The Great Gatsby",
            "authors": [
                {
                    "creativePseudonym": "F. Scott Fitzgerald"
                }
            ],
            "rating": null
        }
    ]
}
```

---
POST: /review, required JSON:
```
{
    "bookID" : 5,
    "description" : "Just a genious book!"
}
```

RESPONSE:

	if successful 
		HttpStatus will be HttpStatus.CREATED
		body JSON: 
		{
			"created" : true,
			"reason": null
		}
	else
		HttpStatus will be HttpStatus.CONFLICT
		body JSON:
		{
			"created" : false,
			"reason": "someReason"
		}

---
POST : /review/evaluate, required JSON:
```
{
    "reviewID" : 5,
    "evaluation" : 1
}
```
// evaluation can be only 1 or -1

RESPONSE: 
```
	if successful 
		HttpStatus will be HttpStatus.CREATED
		body JSON: 
		{
			"created" : true,
			"reason": null
		}
	else
		HttpStatus will be HttpStatus.CONFLICT
		body JSON:
		{
			"created" : false,
			"reason": "someReason"
		}
```		
---
GET: /review/all/{bookID}

RESPONSE:
```
{
    "reviews": [
        {
            "reviewID": 1,
            "userEmail": "Architect@gmail.com",
            "bookID": 5,
            "description": "Just a genious book!",
            "createdAt": "2020-09-16",
            "amountOfPluses": 2,
            "amountOfMinuses": 0
        },
        {
            "reviewID": 3,
            "userEmail": "Lawyer@gmail.com",
            "bookID": 5,
            "description": "OMG wonderful book!",
            "createdAt": "2020-09-17",
            "amountOfPluses": 1,
            "amountOfMinuses": 0
        }
    ]
}
```

---
POST: /ratebook, required JSON : 
```
{
    "bookID" : 1,
    "rating" : 4
}
```

RESPONSE: 
```
	if successful 
		HttpStatus will be HttpStatus.CREATED
		body JSON: 
		{
			"created" : true,
			"reason": null
		}
	else
		HttpStatus will be HttpStatus.CONFLICT
		body JSON:
		{
			"created" : false,
			"reason": "someReason"
		}
```

---
GET : /book/{id}

RESPONSE:
	if such book found, HttpStatus would be HttpStatus.OK, otherwise HttpStatus.NOT_FOUND

```
{
    "bookID": 40,
    "title": "Brave New World",
    "price": 10.25,
    "bookDescription": "Brave New World is a dystopian novel by English author Aldous Huxley, written in 1931 and published in 1932. Largely set in a futuristic World State, inhabited by genetically modified citizens and an intelligence-based social hierarchy, the novel anticipates huge scientific advancements in reproductive technology, sleep-learning, psychological manipulation and classical conditioning that are combined to make a dystopian society which is challenged by only a single individual: the story's protagonist.",
    "imageLink": "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1575509280l/5129._SY475_.jpg",
    "authors": [
        {
            "creativePseudonym": "Aldous Huxley"
        }
    ],
    "genres": [
        "Classics",
        "Dystopia",
        "Fantasy",
        "Fiction",
        "Philosophy",
        "Science Fiction"
    ],
    "reviews": [],
    "rating": null,
    "ratingGivenByUser": null
}
```
	

---
POST: /buybook , required JSON:
```
{
    "bookID" : 1
}
```

RESPONSE:
``` 
	if successful 
		HttpStatus will be HttpStatus.CREATED
		body JSON: 
		{
			"created" : true,
			"reason": null
		}
	else
		HttpStatus will be HttpStatus.CONFLICT
		body JSON:
		{
			"created" : false,
			"reason": "someReason"
		}
```	
	
---
GET : /genre/{genre_name}

RESPONSE:
	if such genre_name found, HttpStatus would be HttpStatus.OK, otherwise HttpStatus.NOT_FOUND


```
{
    "books": [
        {
            "bookID": 28,
            "title": "The Happy Prince",
            "authors": [
                {
                    "creativePseudonym": "Oscar Wilde"
                }
            ],
            "rating": null,
            "genres": [
                "Children's",
                "Classics",
                "Fairy Tales",
                "Fantasy",
                "Fiction",
                "Irish Literature",
                "Short Stories"
            ],
            "price": 19.95,
            "bookDescription": "More than a hundred years ago, Oscar Wilde created this moving story for his children. Now shimmering illustrations, as bejeweled and golden as the Prince himself, give glowing life to the many dimensions of his tale. His story of friendship, love, and a willingness to part with one's own riches may be more important today than ever before. Full color.",
            "imageLink": "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1387700803l/79121.jpg"
        },
        {
            "bookID": 30,
            "title": "The Complete Fairy Tales",
            "authors": [
                {
                    "creativePseudonym": "Oscar Wilde"
                }
            ],
            "rating": null,
            "genres": [
                "Children's",
                "Classics",
                "Fairy Tales",
                "Fantasy",
                "Fiction",
                "Short Stories"
            ],
            "price": 17.69,
            "bookDescription": "The Complete Fairy Tales of Oscar Wilde includes the two definitive story collections The Happy Prince and Other Tales (1888) and A House of Pomegranates (1891). This volume collects exquisite and poignant tales of true beauty, selfless love, generosity, loyalty, brilliant wit, and moral aestheticism, such as \"The Birthday of the Infanta,\" \"The Selfish Giant,\" The Nightingale and the Rose,\" and \"The Happy Prince,\" among others. \n A true classic of wonder for all ages.",
            "imageLink": "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1488665688l/163716._SY475_.jpg"
        }
    ]
}
```

---
GET: /genre/all

RESPONSE:
```
{
    "genres": [
        "Classics",
        "Fantasy",
        "Philosophy",
        "Children's",
        "Fiction",
        "Japanese Literature",
        "Dystopia",
        "Politics",
        "Adult",
        "Inspirational",
        "Animals",
        "Horror",
        "Romance",
        "Historical Fiction",
        "Historical",
        "Thriller",
        "Young Adult",
        "Magic",
        "Paranormal",
        "Adventure",
        "War",
        "Vampires",
        "Literary Fiction",
        "American",
        "Mystery",
        "Suspense",
        "Gothic",
        "Poetry",
        "Short Stories",
        "Fairy Tales",
        "Irish Literature",
        "Plays",
        "Humor",
        "Comedy",
        "Time Travel",
        "Drama",
        "Magical Realism",
        "Russian Literature",
        "Science Fiction",
        "Spanish Literature",
        "Contemporary"
    ]
}
```

---
POST : /search , required JSON:
```
{
    "title" : "someTitle",
    "genres" : [
        "someGenre1",
        "someGenre2"
    ],
    "minPrice" : 10.7,
    "maxPrice" : 20.7,
    "minRating" : 3.5,
    "maxRating" : 5.0
}
```

NOTE: All fields must be filled with some values. For example, if user didn't choose minRating value, you have to set that field with default value 0. 

RESPONSE: 
```
{
    "books": [
        {
            "bookID": 6,
            "title": "Harry Potter and the Chamber of Secrets",
            "authors": [
                {
                    "creativePseudonym": "J.K. Rowling"
                }
            ],
            "rating": 5.0,
            "genres": [
                "Adventure",
                "Children's",
                "Fantasy",
                "Fiction",
                "Magic",
                "Young Adult"
            ],
            "price": 12.25,
            "bookDescription": "Ever since Harry Potter had come home for the summer, the Dursleys had been so mean and hideous that all Harry wanted was to get back to the Hogwarts School for Witchcraft and Wizardry. But just as he’s packing his bags, Harry receives a warning from a strange impish creature who says that if Harry returns to Hogwarts, disaster will strike.\n And strike it does. For in Harry’s second year at Hogwarts, fresh torments and horrors arise, including an outrageously stuck-up new professor and a spirit who haunts the girls’ bathroom. But then the real trouble begins – someone is turning Hogwarts students to stone. Could it be Draco Malfoy, a more poisonous rival than ever? Could it possible be Hagrid, whose mysterious past is finally told? Or could it be the one everyone at Hogwarts most suspects… Harry Potter himself! ",
            "imageLink": "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1474169725l/15881._SY475_.jpg"
        },
        {
            "bookID": 7,
            "title": "Harry Potter and the Sorcerer's Stone",
            "authors": [
                {
                    "creativePseudonym": "J.K. Rowling"
                }
            ],
            "rating": 5.0,
            "genres": [
                "Adventure",
                "Children's",
                "Fantasy",
                "Fiction",
                "Magic",
                "Young Adult"
            ],
            "price": 19.67,
            "bookDescription": "Harry Potter's life is miserable. His parents are dead and he's stuck with his heartless relatives, who force him to live in a tiny closet under the stairs. But his fortune changes when he receives a letter that tells him the truth about himself: he's a wizard. A mysterious visitor rescues him from his relatives and takes him to his new home, Hogwarts School of Witchcraft and Wizardry.\n After a lifetime of bottling up his magical powers, Harry finally feels like a normal kid. But even within the Wizarding community, he is special. He is the boy who lived: the only person to have ever survived a killing curse inflicted by the evil Lord Voldemort, who launched a brutal takeover of the Wizarding world, only to vanish after failing to kill Harry. \n Though Harry's first year at Hogwarts is the best of his life, not everything is perfect. There is a dangerous secret object hidden within the castle walls, and Harry believes it's his responsibility to prevent it from falling into evil hands. But doing so will bring him into contact with forces more terrifying than he ever could have imagined. \n Full of sympathetic characters, wildly imaginative situations, and countless exciting details, the first installment in the series assembles an unforgettable magical world and sets the stage for many high-stakes adventures to come. ",
            "imageLink": "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1474154022l/3._SY475_.jpg"
        }
    ]
}
```
