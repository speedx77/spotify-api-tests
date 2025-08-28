# TO-DO List

- [x] Change POJO to be more dynamic
	- [x] Change artists/items names to fit the various types of search terms (album, playlist, track, show, etc.)
- [] Fix array_has_a_length_of userStep to make it more general
- [x] Try schemaValidation with io.restassured.module.jsv.JsonSchemaValidator;
- [x] try misspelling test case on search (for some reason right now items array returns nothing
	- Search call needed the locale param set to en-us to find result with spelling mistake "Le graet Gtasby"
	- It should be noted without the locale param the item array returns nothing (it should return something, no?) that param has to be required otherwise