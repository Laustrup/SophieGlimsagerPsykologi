const POST = 'POST', GET = 'GET', DELETE = 'DELETE';

async function generateFetch(items) {
    console.log("Request is:",items);
    const response = await fetch(items.url,{
        method: items.method,
        headers: (items.body !== undefined ? {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        } : undefined),
        body: (items.body !== undefined ? JSON.stringify(items.body) : undefined)
    });
    console.log("Response is:",response);
    return response;
}

async function fetchElement(items) {
    const response = await generateFetch(items);
    const element = (await response.json()).element;
    console.log("Elements converted from response:",element);
    return element;
}