const TEXT_INPUT = "text", NUMBER_INPUT = "number", DATE_INPUT = "date", TIME_INPUT = "time", DATE_TIME_INPUT = "datetime-local";

function include(element) {
    if (element.doInclude !== undefined)
        return element.doInclude ? element.value : ``;
}

function inputField(field) {
    return `
        <div class="input_field">
            ${include({
                doInclude: field.label !== undefined,
                value: `<label for="${field.id}">${field.label}</label>`
            })}
            <input type="${field.type}" placeholder="${field.placeholder}"
                ${include({
                doInclude: field.isRequired,
                value: `required`
                })}
                ${include({
                    doInclude: field.max,
                    value: `max="${field.max}"`
                })}
                ${include({
                    doInclude: field.min,
                    value: `min="${field.min}"`
                })}
                ${include({
                    doInclude: field.class,
                    value: `class="${field.class}"`
                })}
                >
        </div>
    `;
}