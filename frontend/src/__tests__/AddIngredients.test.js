import {render, screen} from "@testing-library/react";
import Ingredients from "../pages/Ingredients";

describe('Ingredients', function () {

    it('Test renders correctly', async function () {
        render(<Ingredients/>);
        expect(screen.getByRole('heading', {
            name: /Ingredients/i
        })).toHaveTextContent("Ingredients");
    });

});