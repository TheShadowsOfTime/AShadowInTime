package com.cjs07.ashadowintime.files;

/**
 * Created by CJ on 1/27/14.
 * Developed for the A-Shadow-In-Time project.
 */
public class LevelFile {
  
    FileInputStream file;
    InputStreamReader reader;

    Scanner levelBuilder;

    Level level = new Level();

    public Level createLevel (String fileName)
    {
        try
        {
            file = new FileInputStream("level/" + fileName + ".ast");
            reader = new InputStreamReader(file);

            levelBuilder = new Scanner(reader);

            level.map = new int[100][100];

            int x = 0;
            int y = 0;

            while(levelBuilder.hasNext())
            {
                level.map[x][y] = scanner.nextInt();

                if (x < 100 - 1)
                {
                    x++;
                }
                else
                {
                    y++;

                    x = 0;
                }
            }

            return level;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
