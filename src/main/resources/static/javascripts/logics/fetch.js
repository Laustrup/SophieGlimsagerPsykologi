/**
 * Represents a mapping type.
 * Is created to avoid misspelling.
 * @type {string}
 */
const POST = 'POST', GET = 'GET', DELETE = 'DELETE';

/**
 * Fetches from the given information in items, also logs Request and Response.
 * @param items The information needed for the fetch, with inputs of:
 *              url, method and body.
 * @return {Promise<Response>} The response of the fetch();
 */
async function generateFetch(items) {
    console.log("Request is:",items);
    const response = await fetch(items.url,{
        method: items.method,
        headers: (items.body !== undefined ? {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        } : undefined),
        body: (items.body !== undefined ? (items.body !== typeof String ? JSON.stringify(items.body) : items.body) : undefined)
    });
    console.log("Response is:",response);

    return response;
}

/**
 * Performs a fetch() and afterwards gets the json of that response into a const. Will log the response.
 * @param items The information needed for the fetch, with inputs of:
 *              url, method and body.
 * @return {Promise<*|jQuery|HTMLElement|"anyfunc"|"externref">} The response as a const.
 */
async function fetchElement(items) {
    const response = await generateFetch(items);
    const element = await response.json();

    console.log("Elements converted from response:",element);

    return element;
}